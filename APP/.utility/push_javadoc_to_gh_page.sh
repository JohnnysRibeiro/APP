echo -e "Starting JavaDoc publish script."

if [ "$TRAVIS_REPO_SLUG" == "JohnnysRibeiro/APP" ] && [ "$TRAVIS_JDK_VERSION" == "oraclejdk7" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then

echo -e "Publishing javadoc...\n"
cp -R build/docs/javadoc $HOME/javadoc-latest
cd $HOME
git config --global user.email "travis@travis-ci.org"
git config --global user.name "travis-ci"
git clone --quiet --branch=gh-pages https://${GH_TOKEN}@github.com/JohnnysRibeiro/APP gh-pages > /dev/null
cd gh-pages
git rm -rf ./javadoc
cp -Rf $HOME/javadoc-latest ./javadoc
git add -f .
git commit -m "Lastest javadoc on travis build $TRAVIS_BUILD_NUMBER auto-pushed to gh-pages"
git push -fq origin gh-pages > /dev/null
echo -e "Published Javadoc to gh-pages.\n"

else

echo -e "Could not publish JavaDoc."

fi
