#!/usr/bin/env bash
set -euo pipefail

TOOLS="$HOME/tools"
BT="$TOOLS/bt/android-14"
AJ="$TOOLS/pf/android-34/android.jar"
JDK_RUNTIME="$HOME/.local/lib/python3.12/site-packages/jdk4py/java-runtime/bin"
export PATH="$JDK_RUNTIME:$PATH"

ROOT="$(cd "$(dirname "$0")" && pwd)"
OUT="$ROOT/build"
rm -rf "$OUT"
mkdir -p "$OUT/classes" "$OUT/dex"

"$BT/aapt2" compile --dir "$ROOT/res" -o "$OUT/res.zip"
"$BT/aapt2" link \
    -o "$OUT/base.apk" \
    -I "$AJ" \
    --manifest "$ROOT/AndroidManifest.xml" \
    --min-sdk-version 24 \
    --target-sdk-version 34 \
    "$OUT/res.zip"

java -jar "$TOOLS/ecj.jar" -1.8 -nowarn \
    -cp "$AJ" \
    -d "$OUT/classes" \
    "$ROOT"/src/com/vibe/periodictable/*.java

java -cp "$BT/lib/d8.jar" com.android.tools.r8.D8 \
    --release \
    --lib "$AJ" \
    --min-api 24 \
    --output "$OUT/dex" \
    $(find "$OUT/classes" -name '*.class')

python3 - "$OUT/base.apk" "$OUT/dex/classes.dex" "$OUT/unsigned.apk" <<'EOF'
import sys, zipfile, shutil
base, dex, dest = sys.argv[1], sys.argv[2], sys.argv[3]
shutil.copyfile(base, dest)
with zipfile.ZipFile(dest, 'a') as z:
    z.write(dex, 'classes.dex')
EOF

"$BT/zipalign" -f -p 4 "$OUT/unsigned.apk" "$OUT/aligned.apk"

if [ ! -f "$ROOT/debug.keystore" ]; then
    keytool -genkeypair \
        -keystore "$ROOT/debug.keystore" \
        -alias periodic \
        -storepass periodic \
        -keypass periodic \
        -keyalg RSA -keysize 2048 -validity 10000 \
        -dname "CN=Periodic Table, O=Vibe, C=TR" >/dev/null 2>&1
fi

java -cp "$BT/lib/apksigner.jar" com.android.apksigner.ApkSignerTool sign \
    --ks "$ROOT/debug.keystore" \
    --ks-key-alias periodic \
    --ks-pass pass:periodic \
    --key-pass pass:periodic \
    --min-sdk-version 24 \
    --out "$ROOT/PeriyodikTablo.apk" \
    "$OUT/aligned.apk"

java -cp "$BT/lib/apksigner.jar" com.android.apksigner.ApkSignerTool verify --print-certs "$ROOT/PeriyodikTablo.apk"
ls -lh "$ROOT/PeriyodikTablo.apk"
