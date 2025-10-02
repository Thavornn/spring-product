pipeline {
    agent {
        kubernetes {
            label 'docker-agent'
            defaultContainer 'docker'
            yaml """
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: docker
    image: docker:24-dind
    securityContext:
      privileged: true
"""
        }
    }
    environment {
        DOCKERHUB_CRED = 'dockerhub-token'
        GITHUB_CRED    = 'github-token'
        DOCKER_REG     = 'phengbunath'
        IMAGE_REPO     = "${env.DOCKER_REG}/spring-product"
        IMAGE_TAG      = "${env.BUILD_NUMBER}"
        CD_BRANCH      = 'main'
    }
    stages {
        stage('Build & Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-token', usernameVariable: 'DH_USER', passwordVariable: 'DH_PASS')]) {
                    sh """
                        echo \$DH_PASS | docker login -u \$DH_USER --password-stdin
                        docker build -t ${IMAGE_REPO}:${IMAGE_TAG} .
                        docker push ${IMAGE_REPO}:${IMAGE_TAG}
                        docker logout
                    """
                }
            }
        }
        stage('Update Helm Chart') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'github-token', usernameVariable: 'GH_USER', passwordVariable: 'GH_TOKEN')]) {
                    sh """
                        git clone -b ${CD_BRANCH} https://${GH_USER}:${GH_TOKEN}@github.com/BunathPheng/CD-product-service.git cd-repo
                        cd cd-repo
                        git config user.name "BunathPheng"
                        git config user.email "bunathpheng4@gmail.com"
                        sed -i "s|tag: .*|tag: ${IMAGE_TAG}|" values.yaml
                        git add values.yaml
                        git commit -m "Update image tag to ${IMAGE_TAG}"
                        git push https://${GH_USER}:${GH_TOKEN}@github.com/BunathPheng/CD-product-service.git ${CD_BRANCH}
                    """
                }
            }
        }
    }
}
