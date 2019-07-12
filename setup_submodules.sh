#/bin/sh

if [ "$*" == "" ] || [ "$#" -ne 2 ]; then
    echo "Error: No arguments provided. Please supply your github id followed by the branch id."
    echo "Example: "
    echo "         setup_modules.sh indrajra release-1.0.0"
    exit 1
fi

exit 1

export modName=sunbird-user-registry
echo "Setting up submodule " $modName

git submodule deinit -f -- $modName

echo "Deinited and rm complete"
rm -rf .git/modules/$modName

echo "Rebase: $1"
git submodule -b $2 add https://github.com/$1/sunbird-user-registry.git $modName

echo "Updating submodule"
git submodule update --init --remote

unset modName

echo "done setting submodules..."

./setVars.sh