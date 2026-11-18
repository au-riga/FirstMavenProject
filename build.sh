#!/usr/bin/env bash
# Сборка библиотеки, прогон тестов и деплой (jar + sources) в ~/maven-repo

set -euo pipefail
cd "$(dirname "$0")"

REPO_DIR="$HOME/maven-repo/org/example/FirstMavenProject/1.0-SNAPSHOT"

echo ">>> Сборка, тесты и деплой FirstMavenProject..."
mvn -q clean deploy -P local-deploy

echo ""
echo "=== ГОТОВО ==="
echo "Библиотека задеплоена в: $REPO_DIR"
echo "Свежие файлы:"
ls -1t "$REPO_DIR" | grep '\.jar$' | head -2
