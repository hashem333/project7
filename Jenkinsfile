def gv
pipeline {
	agent any
	parameters {
		string(name: 'VERSION', defaultValue: '1.0', description: 'Application version')
	}
	stages {

		stage("init") {
				steps {
					script {
						gv = load "script.groovy"
					}
				}
		}
		stage("test") {
				steps {
					script {
						gv.testApp()
					}
				}
		}


		stage("build") {
				steps {
					script {
						gv.buildDockerImage()
					}
				}
		}


		stage("deploy") {
				steps {
					script {
						gv.deployDockerImage()
					}
				}
		}

	}
}
