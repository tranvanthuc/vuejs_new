# vue-project

This template should help get you started developing with Vue 3 in Vite.

## Recommended IDE Setup

[VSCode](https://code.visualstudio.com/) + [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar) (and disable Vetur) + [TypeScript Vue Plugin (Volar)](https://marketplace.visualstudio.com/items?itemName=Vue.vscode-typescript-vue-plugin).

## Customize configuration

See [Vite Configuration Reference](https://vitejs.dev/config/).

## Project Setup

```sh
npm install
```

### Compile and Hot-Reload for Development

```sh
npm run dev
```

### Compile and Minify for Production

```sh
npm run build
```

### Run Unit Tests with [Vitest](https://vitest.dev/)

```sh
npm run test:unit
```

### Lint with [ESLint](https://eslint.org/)

```sh
npm run lint
```

### Jenkins Plugins

https://www.jenkins.io/doc/pipeline/steps/ssh-steps/

https://www.jenkins.io/doc/pipeline/steps/credentials-binding/

https://plugins.jenkins.io/slack/

https://plugins.jenkins.io/pipeline-utility-steps/

https://plugins.jenkins.io/multibranch-scan-webhook-trigger/

https://plugins.jenkins.io/ignore-committer-strategy

### Create infra & install packages

- Use template dev

```
cd /Users/tranvanthuc/Documents/projects/temp/terraform/template/dev
```

- This command will create server and run ansible

```
terraform apply --auto-approve
```

- Run ansible-playbook

```
cd /Users/tranvanthuc/Documents/projects/temp/ansible/flow-jenkins
```

- Run script to deploy server dev
```
ansible-playbook playbook_deploy_dev.yaml
```

- Dynamic inventory
```
ansible-inventory -i aws_ec2.yaml --graph
```

### Jenkins to run Ansible
- Set aws credentials inside Jenkins container

- Store ec2-key on Jenkins (Username/Private key)