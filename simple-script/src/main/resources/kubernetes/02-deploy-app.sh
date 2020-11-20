# https://kubernetes.io/zh/docs/tutorials/kubernetes-basics/deploy-app/deploy-interactive/

# first insure you have kubectl
kubectl -version

kubectl --help

# create deployment
# kubernetes-bootcamp is name, --image from docker-hub
# this commmand deployed your application by creating a deployment.
kubectl create deployment kubernetes-bootcamp --image=gcr.io/google-samples/kubernetes-bootcamp:v1

:<<EOF
next
1.searched for a suitable node where an instance of the application could be run
note we have only 1 available node because of minikube
2.scheduled the application to run on that Node
3.configured the cluster to reschedule the instance on a new Node when needed
EOF

# get list of deployments
kubectl get deployments

:<<EOF
NAME                  READY   UP-TO-DATE   AVAILABLE   AGE
kubernetes-bootcamp   1/1     1            1           104s
EOF

# The kubectl command can create a proxy that will forward communications into the cluster-wide, private network
# proxy should open in another terminal
kubectl proxy

# now we can send request interactive with the application
# then we get response of this app
curl http://localhost:8001/version

# export set current terminal variable
export POD_NAME=$(kubectl get pods -o go-template --template '{{range .items}}{{.metadata.name}}{{"\n"}}{{end}}')

:<<EOF
the result of POD_NAME
kubernetes-bootcamp-69fbc6f4cf-sg5sh
EOF