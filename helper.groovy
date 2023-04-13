def installPackage() {
    echo 'Installing with npm'
    // sh 'yarn'
}

def build() {
    echo 'Building ...'
}

def buildImage(String type) {
    def newVersion = common.updateVersion(
        file: 'package.json',
        type: type
    )
    echo "New Version $newVersion"
    env.newVersion = newVersion
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
    dir('ansible') {
        withCredentials([
            sshUserPrivateKey(
                credentialsId: 'ec2-key', 
                usernameVariable: 'USER', 
                keyFileVariable: 'KEY_FILE'
            )
        ]) {
             sh 'cp $KEY_FILE ec2_key.pem'
            sh 'chmod 400 ec2_key.pem'
        }
        sh 'ansible-playbook playbook_deploy_dev.yaml'
    }
}

def commitVersion() {
    echo "New version : $newVersion"
    common.commitVersion(
        credentialsId: 'tranvanthuc-github',
        email: 'thuctran@gmail.com',
        username: 'thuctran',
        remoteUrl: 'github.com/tranvanthuc/vuejs_new.git',
        version: newVersion
    )
}


return this
