# Removing directories like `.idea`, `bin` and `obj`

Get-ChildItem -Path . -Directory -Filter ".idea" -Recurse | ForEach-Object { Remove-Item -Path $_.FullName -Recurse -Force }
Get-ChildItem -Path . -Directory -Filter "bin" -Recurse | ForEach-Object { Remove-Item -Path $_.FullName -Recurse -Force }
Get-ChildItem -Path . -Directory -Filter "obj" -Recurse | ForEach-Object { Remove-Item -Path $_.FullName -Recurse -Force }

# java: removing directories like `.ide`, '`target`

Get-ChildItem -Path . -Directory -Filter ".idea" -Recurse | ForEach-Object { Remove-Item -Path $_.FullName -Recurse -Force }
Get-ChildItem -Path . -Directory -Filter "target" -Recurse | ForEach-Object { Remove-Item -Path $_.FullName -Recurse -Force }

