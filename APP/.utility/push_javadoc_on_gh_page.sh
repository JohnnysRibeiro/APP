
invokeJavadoc=false
# Only invoke the javadoc deployment process
# for the first job in the build matrix, so as
# to avoid multiple deployments.
if [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then
case "${TRAVIS_JOB_NUMBER}" in
*\.1)
echo -e "Invoking Javadoc deployment for Travis job ${TRAVIS_JOB_NUMBER}"
invokeJavadoc=true;;
esac
fi
if [ "$invokeJavadoc" == true ]; then
echo -e "Start to publish lastest Javadoc to gh-pages...\n"
echo -e "Invoking Maven to generate the site documentation...\n"
mvn site site:stage -q -ff -B -P nocheck
echo -e "Copying the generated docs over...\n"
cp -R target/staging $HOME/javadoc-latest
cd $HOME
git config --global user.email "travis@travis-ci.org"
git config --global user.name "travis-ci"
echo -e "Cloning the gh-pages branch...\n"
git clone --quiet --branch=gh-pages https://${GH_TOKEN}@github.com/JohnnysRibeiro/APP gh-pages > /dev/null
cd gh-pages
echo -e "Removing javadocs...\n"
git rm -rf ./current/javadocs > /dev/null
git rm -rf ./development/javadocs > /dev/null
echo -e "Copying new javadocs to current...\n"
cp -Rf $HOME/javadoc-latest ./development/javadocs
echo -e "Adding changes to the index...\n"
git add -f . > /dev/null
echo -e "Committing changes...\n"
git commit -m "Latest javadoc on successful travis build $TRAVIS_BUILD_NUMBER auto-pushed to gh-pages" > /dev/null
echo -e "Pushing upstream to origin...\n"
git push -fq gh-pages > /dev/null
echo -e "Successfully published Javadocs to [gh-pages] branch.\n"
fi