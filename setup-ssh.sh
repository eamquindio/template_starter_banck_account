#!/bin/bash

echo "=== Configuración de SSH para GitHub ==="
echo ""

# Crear directorio .ssh si no existe
mkdir -p ~/.ssh
chmod 700 ~/.ssh

# Solicitar email
read -p "Ingresa tu email de GitHub: " email

# Generar clave SSH
echo "Generando clave SSH..."
ssh-keygen -t ed25519 -C "$email" -f ~/.ssh/id_ed25519 -N ""

# Iniciar ssh-agent
echo "Iniciando ssh-agent..."
eval "$(ssh-agent -s)"

# Crear archivo de configuración SSH
echo "Creando configuración SSH..."
cat > ~/.ssh/config << EOF
Host github.com
  AddKeysToAgent yes
  UseKeychain yes
  IdentityFile ~/.ssh/id_ed25519
EOF

# Agregar clave al ssh-agent
ssh-add --apple-use-keychain ~/.ssh/id_ed25519

# Mostrar la clave pública
echo ""
echo "=== Tu clave SSH pública ==="
echo "Copia todo el siguiente texto y agrégalo a GitHub en:"
echo "https://github.com/settings/keys"
echo ""
echo "------- INICIO DE LA CLAVE -------"
cat ~/.ssh/id_ed25519.pub
echo "------- FIN DE LA CLAVE -------"
echo ""
echo "Después de agregar la clave a GitHub, presiona Enter para continuar..."
read

# Cambiar el remote a SSH
echo "Cambiando remote a SSH..."
cd /Users/caferrerb/IdeaProjects/template_bank_account
git remote set-url origin git@github.com:eamquindio/simple_bank_account.git

# Probar conexión
echo "Probando conexión SSH con GitHub..."
ssh -T git@github.com

echo ""
echo "✅ Configuración completada!"
echo "Ahora puedes usar: git push origin main"