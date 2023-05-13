def testApp() {
	echo "testing the application..."
}
def buildDockerImage() {
	echo "building the docker image..."
	withCredentials([usernamePassword(credentialsId: 'my-dockerhub-repo', usernameVariable: 'USER', passwordVariable: 'PASS')])
	{
		sh "docker build -t hashem333/my-docker-repo:project7-new-1.0 ."
		sh "echo $PASS | docker login -u $USER --password-stdin"
		sh "docker push hashem333/my-docker-repo:project7-new-1.0"
		sh "docker rmi hashem333/my-docker-repo:project7-new-1.0 --force" 
		}
}
def deployDockerImage() {
	sh "ssh hashem@127.0.0.1 docker pull hashem333/my-docker-repo:project7-new-1.0"
	sh "ssh hashem@127.0.0.1 docker run --rm -dt -p 8088:8088 --name project7 hashem333/my-docker-repo:project7-new-1.0"
}

return this
