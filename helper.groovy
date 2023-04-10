def installPackage() {
    echo 'Installing with npm'
    // sh 'yarn'
}

def build() {
    echo 'Building ...'
}

def buildImage(String type) {
    echo "Testing app ... with $BRANCH_NAME"
    def newVersion = common.updateVersion(
        file: 'package.json',
        type: type
    )
    echo "New Version $newVersion"
    docker_util.buildImage(
        creId: 'docker_hub', 
        imageName: 'vuejs_app', 
        version: newVersion
    )
}

def deploy() {
    // withCredentials([sshUserPrivateKey(
    //     credentialsId: 'vuejs-server', 
    //     usernameVariable: 'USER', 
    //     keyFileVariable: 'KEY_FILE'),
    //     string(credentialsId: 'vuejs-server-ip', variable: 'EC2_IP')
    // ]) {
    //     def remote = [:]
    //     remote.name = USER
    //     remote.host = EC2_IP
    //     remote.user = USER
    //     remote.identityFile = KEY_FILE
    //     remote.allowAnyHosts = true
    //     sshCommand remote: remote, command: "./deployfe.sh"
    //     slackSend(color: 'good', message: "Deployed to server ${BRANCH_NAME}")
    // }
    echo 'Deploying ...'
}


return this
