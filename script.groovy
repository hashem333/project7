def testApp() {
	echo "testing the application..."
}
def buildDockerImage() {
	echo "building the docker image..."
	withCredentials([usernamePassword(credentialsId: 'my-dockerhub-repo', usernameVariable: 'USER', passwordVariable: 'PASS')])
	{
		sh "docker build -t 172.18.0.2:8085/my-docker-repo/project7-new:1.0 ."
		sh "echo $PASS | docker login 172.18.0.2:8085 -u $USER --password-stdin"
		sh "docker push 172.18.0.2:8085/my-docker-repo/project7-new:1.0"
		sh "docker rmi 172.18.0.2:8085/my-docker-repo/project7-new:1.0 --force" 
		}
}
def deployDockerImage() {
	sh "ssh hashem@172.17.0.1 docker pull 172.18.0.2:8085/my-docker-repo/project7-new:1.0"
	sh "ssh hashem@172.17.0.1 docker run --rm -dt -p 8088:8088 --name project7 172.18.0.2:8085/my-docker-repo/project7-new:1.0"
}

return this
