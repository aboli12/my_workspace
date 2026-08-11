# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Repository structure

This is a personal workspace with two unrelated areas — check which one a task touches before assuming shared tooling or conventions:

- `practice/dsa/leetcode/` — standalone Java files, one per LeetCode problem (e.g. `_0001_TwoSum.java`, `_0217_ContainsDuplicate2.java`). Each file is self-contained with its own `main`/solution class; there is no shared build file, package structure, or test runner. Compile/run individual files directly with `javac`/`java` if needed.
- `ai/certifications/uigen/` — a full Next.js application (see below). This is where almost all substantive engineering work happens.
- `ai/certifications/cca_f/` — currently empty.

## uigen app (`ai/certifications/uigen/`)

AI-powered React component generator: chat with an LLM, it generates/edits component files in an in-memory virtual file system, and the result renders live in an in-browser preview — no files are ever written to disk.

All commands below are run from `ai/certifications/uigen/`.

### Commands

```bash
npm run setup       # install deps + prisma generate + prisma migrate dev (first-time setup)
npm run dev         # start dev server (Next.js + Turbopack) on localhost:3000
npm run build       # production build
npm run lint        # next lint
npm test            # run vitest test suite once (interactive watch by default; add --run for CI-style single pass)
npm run db:reset    # reset the SQLite dev database (prisma migrate reset --force)
```

Run a single test file with `npx vitest run path/to/file.test.tsx`. Tests use `jsdom` and live in `__tests__` folders beside the code they test (e.g. `src/lib/transform/__tests__`, `src/components/chat/__tests__`).

**Never run `npm audit fix`.** Dependencies are pinned to versions known to work together; `audit fix` can bump past compatible versions and break the app. Security issues are fixed by bumping the pinned version directly instead.

No API key is required to run the app: without `ANTHROPIC_API_KEY` set in `.env` (or left as the placeholder), `getLanguageModel()` (`src/lib/provider.ts`) returns a `MockLanguageModel` that plays back canned tool calls/responses instead of calling Claude. Keep this fallback working when touching the provider or chat route — it's what most local dev and tests run against.

### Architecture

**Virtual file system, not disk I/O.** `src/lib/file-system.ts` (`VirtualFileSystem`) implements an in-memory tree (paths → `FileNode`, with a `Map`-based directory structure) supporting create/read/update/delete/rename plus text-editor-style operations (`viewFile`, `str_replace`-equivalent `replaceInFile`, `insertInFile`). Generated projects are never written to the real filesystem. A fresh `VirtualFileSystem` is reconstructed per-request in the chat API route from the client-sent serialized state (`deserializeFromNodes`), and re-serialized back into the response/DB (`serialize`).

**Chat flow drives file edits via tool calls.** `src/app/api/chat/route.ts` is the single entry point: it takes `{ messages, files, projectId }`, rehydrates the `VirtualFileSystem` from `files`, prepends a system prompt (`src/lib/prompts/generation.tsx`), and calls Vercel AI SDK's `streamText` with two tools bound to that file system instance:
  - `str_replace_editor` (`src/lib/tools/str-replace.ts`) — Anthropic-style text-editor tool: `view` / `create` / `str_replace` / `insert` (`undo_edit` is explicitly unsupported).
  - `file_manager` (`src/lib/tools/file-manager.ts`) — higher-level file ops (rename/delete/etc.).

  On stream finish, if `projectId` is present and the user has a valid session, the updated messages and serialized file system are persisted to the `Project` row (Prisma). Anonymous users get no persistence — see `src/lib/anon-work-tracker.ts` for how anonymous in-progress work is tracked client-side instead.

**Live preview is fully client-side, no bundler.** `src/lib/transform/jsx-transformer.ts` transforms each virtual file's JS/JSX/TS/TSX with `@babel/standalone` (React automatic runtime + TypeScript preset) in the browser, wraps each transformed module in a blob URL, and builds a browser-native `importmap` (`createImportMap`) mapping local paths (including `@/`-alias variants and extension-less variants) and third-party packages (proxied to `esm.sh`) to those blob URLs. Unresolved local imports get an auto-generated placeholder module (`createPlaceholderModule`) so the preview doesn't hard-crash on a missing file. `createPreviewHTML` assembles the final iframe document (Tailwind via CDN script, an `ErrorBoundary`, and inline syntax-error reporting when a file fails to transform). Rendered inside `src/components/preview/PreviewFrame.tsx`.

**Auth is custom JWT-in-cookie, not a library.** `src/lib/auth.ts` signs/verifies a JWT (via `jose`) containing `{ userId, email, expiresAt }` in an httpOnly `auth-token` cookie. `src/middleware.ts` gates `/api/projects` and `/api/filesystem` behind a valid session; other routes do their own `getSession()` checks server-side as needed. There's no separate auth provider/service — `createSession`/`getSession`/`verifySession`/`deleteSession` are the whole surface.

**Persistence is minimal by design.** Prisma/SQLite (`prisma/schema.prisma`) has just two models: `User` and `Project`. A `Project` stores its entire chat history and file system as JSON strings (`messages`, `data` columns) rather than normalized tables — the virtual file system and message list are treated as opaque blobs that get serialized/deserialized wholesale on load/save.

**Path alias:** `@/*` maps to `src/*` (see `tsconfig.json`), used throughout imports and mirrored in the runtime import map generation above.
