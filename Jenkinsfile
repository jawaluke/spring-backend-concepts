pipeline {
    agent any
    tools {
        jdk 'jdk_1.8_local'
        maven 'maven_3.9.14'
    }
    stages {
        stage('Check Version') {
            steps {
                bat 'java -version'
            }
        }

        stage('Build') {
            steps {
                // Now 'mvn' is available in the shell path
                bat 'mvn clean package -DskipTests'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }

}
