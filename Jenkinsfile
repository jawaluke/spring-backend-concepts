pipeline {
    agent any
    tools {
        jdk 'jdk_1.8_local'
        maven 'maven_3.9.14'
    }
    stages {
        stage('Check Version') {
            steps {
                sh 'java -version'
            }
        }

        stage('Build') {
            steps {
                // Now 'mvn' is available in the shell path
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }

}
