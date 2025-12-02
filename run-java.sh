#!/usr/bin/env bash
set -euo pipefail

usage() {
  cat <<EOF
Usage: $(basename "$0") <ClassOrFile> [args...]

Examples:
  $(basename "$0") Grap            # compile Grap.java (if present) and run
  $(basename "$0") Grap.java       # compile Grap.java and run
  $(basename "$0") com.example.App # run compiled class or compile com/example/App.java
EOF
  exit 2
}

if [[ ${#} -lt 1 ]]; then
  usage
fi

target="$1"; shift

# helper: get package from a .java file (first package declaration)
get_package() {
  local f="$1"
  grep -m1 "^[[:space:]]*package " "$f" 2>/dev/null | sed -E 's/^[[:space:]]*package[[:space:]]+([^;]+);.*$/\1/' || true
}

OUTDIR="./out"
mkdir -p "$OUTDIR"

run_fqcn() {
  local fqcn="$1"; shift
  java -cp "$OUTDIR":. "$fqcn" "$@"
}

if [[ "$target" == *.java || -f "$target" ]]; then
  # user passed a filename or a path
  if [[ "$target" == *.java ]]; then
    file="$target"
  else
    file="$target"
  fi

  if [[ ! -f "$file" ]]; then
    echo "File not found: $file" >&2
    exit 1
  fi

  classname=$(basename "$file" .java)
  pkg=$(get_package "$file")
  fqcn="$classname"
  if [[ -n "$pkg" ]]; then fqcn="$pkg.$classname"; fi

  javac -d "$OUTDIR" "$file"
  run_fqcn "$fqcn" "$@"
  exit $?
fi

# If target is not a file, treat as class name (possibly fully-qualified)
classname="$target"

# If class file exists under out (path from fqcn), run it
classpath_file="$OUTDIR/${classname//.//}.class"
if [[ -f "$classpath_file" ]]; then
  run_fqcn "$classname" "$@"
  exit $?
fi

# Try to find a .java file for this simple classname in cwd
if [[ -f "${classname}.java" ]]; then
  file="${classname}.java"
  pkg=$(get_package "$file")
  fqcn="$classname"
  if [[ -n "$pkg" ]]; then fqcn="$pkg.$classname"; fi
  javac -d "$OUTDIR" "$file"
  run_fqcn "$fqcn" "$@"
  exit $?
fi

echo "Can't find class or source for '$target' in current directory." >&2
echo "Try passing a .java file path, a simple class name (with source), or a fully-qualified class name." >&2
exit 1
