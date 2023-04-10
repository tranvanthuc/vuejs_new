#!/usr/bin/env groovy

// dynamic import
library identifier: 'jenkins-share-1@main', retriever: modernSCM(
  [$class: 'GitSCMSource',
   remote: 'https://github.com/tranvanthuc/jenkins-share-1.git',
   credentialsId: 'tranvanthuc-github']) _

def helper

def input_params

pipeline {
    agent any
    tools {
        nodejs "node_19" // name tools
    }

    stages {
        stage('Pre-build') {
            steps {
                echo "${env.getEnvironment()}"
                script {
                    echo 'Load helper groovy file.'
                    helper = load "helper.groovy"
                }
            }
        }
        stage('Install package') {
            steps {
                script {
                    helper.installPackage()
                }
            }
        }
        stage('Build') {
            steps {
                script {
                    helper.build()
                }
            }
        }
        stage('Build Image') {
            when {
                expression {
                    BRANCH_NAME == "main"
                }
            }
            steps {
                script {
                    // timeout(time: 1, unit: 'MINUTES') {
                    //     input_params = input message: 'Do you want to deploy?', parameters: [
                    //         choice(name: 'type_version', choices: ['Major', 'Minor', 'Patch'], description: 'Choose type of version:')
                    //     ]
                    // }
                    helper.buildImage('PATCH')
                }
            }
        }
        stage('Deploy') {
            when {
                expression {
                    BRANCH_NAME == "main"
                }
            }
            steps {
                script {
                    helper.deploy()
                }
            }
        }
    }
    post {
        // Clean after build
        always {
            cleanWs(
                cleanWhenNotBuilt: false,
                deleteDirs: true,
                disableDeferredWipeout: true,
                notFailBuild: true,
                skipWhenFailed: true
            )
        }
        success {
            echo 'I succeeded! '
            script {
                slack_util.sendToSlack()
            }
        }
        unstable {
            echo 'I am unstable :/'
        }
        failure {
            script {
                slack_util.sendErrorMessage('Failed when build & deploy!')
            }
        }
        changed {
            echo 'Things were different before...'
        }
    }
}
