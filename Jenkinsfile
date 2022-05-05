pipeline {
  agent {label 'gce'}
  tools {
    maven 'Maven-3.8.4'
  }
  stages {
    stage ('Build') {
      steps {
        sh 'echo "Generating artifacts for ${BUILD_NUMBER}" > output.txt'
        sh 'mvn clean package'
      }
    }
    stage('Test') {
      steps {
        sh 'mvn test'
      }
      post {
        always {
          junit 'target/surefire-reports/*.xml'
        }
      }
    }
    stage('Archive'){
      steps {
        dir('target'){
          archiveArtifacts allowEmptyArchive: true, artifacts: '**', fingerprint: true, followSymlinks: false, onlyIfSuccessful: true
        }
      }
    }
  }
}
