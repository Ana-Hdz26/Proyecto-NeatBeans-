#!/bin/bash

# Directorios a respaldar
directorios=("/home" "/var" "/etc")

# Directorio de respaldos
directorio_respaldos="/respaldos"

# Obtener la fecha y hora actual
fecha_hora=$(date +"%Y%m%d_%H%M%S")

# Crear el directorio de respaldos con la fecha actual si no existe
mkdir -p "$directorio_respaldos/$fecha_hora"

# Realizar el respaldo de cada directorio
for dir in "${directorios[@]}"; do
  nombre_archivo=$(basename "$dir")
  tar -czf "$directorio_respaldos/$fecha_hora/${nombre_archivo}_${fecha_hora}.tar.gz" "$dir"
done

echo "Respaldo completado."

