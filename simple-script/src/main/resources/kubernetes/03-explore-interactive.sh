# https://kubernetes.io/docs/tutorials/kubernetes-basics/explore/explore-intro/

# before start ,you need to understand pods and nodes concept

# git list of nodes
kubectl get nodes
kubectl get pods
kubectl get deployments

# get detail of pods, of cause you can see other info(example:nodes,deployments)
kubectl describe pods

:<<EOF
Pods are running in an isolated, private network -
so we need to proxy access to them so we can debug and interact with them
EOF
kubectl proxy

# as we have done in 02-deploy-app,we export $POD_NAME in current terminal
kubectl logs "$POD_NAME"

# we use the exec command and use the name of the Pod as a parameter.
# Let’s list the environment variables:
kubectl exec "$POD_NAME" env

# now we can go into container
kubectl exec -ti "$POD_NAME" bash

# we can exlpore and interactive with itself
curl localhost:8080

# close your container connection type
exit