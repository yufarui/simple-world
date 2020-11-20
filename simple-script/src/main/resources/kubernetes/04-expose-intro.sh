# https://kubernetes.io/docs/tutorials/kubernetes-basics/expose/expose-intro/

kubectl get services
:<<EOF
NAME         TYPE        CLUSTER-IP   EXTERNAL-IP   PORT(S)   AGE
kubernetes   ClusterIP   10.96.0.1    <none>        443/TCP   36s
EOF

# expose new service
kubectl expose deployment/kubernetes-bootcamp --type="NodePort" --port 8080

:<<EOF
NAME                  TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)          AGE
kubernetes            ClusterIP   10.96.0.1       <none>        443/TCP          3m45s
kubernetes-bootcamp   NodePort    10.105.30.224   <none>        8080:32323/TCP   9s
EOF

kubectl describe services kubernetes-bootcamp

:<<EOF
Name:                     kubernetes-bootcamp
Namespace:                default
Labels:                   run=kubernetes-bootcamp
Annotations:              <none>
Selector:                 run=kubernetes-bootcamp
Type:                     NodePort
IP:                       10.105.30.224
Port:                     <unset>  8080/TCP
TargetPort:               8080/TCP
NodePort:                 <unset>  32323/TCP
Endpoints:                172.18.0.3:8080
Session Affinity:         None
External Traffic Policy:  Cluster
Events:                   <none>
EOF

export NODE_PORT=$(kubectl get services/kubernetes-bootcamp -o go-template='{{(index .spec.ports 0).nodePort}}')

# result is NodePort:32323
echo NODE_PORT="$NODE_PORT"

# we get a response from the server. The Service is exposed.
curl $(minikube ip):"$NODE_PORT"

# find service by lable
kubectl get services -l run=kubernetes-bootcamp

# we can add lable to pod
kubectl label pod "$POD_NAME" app=v1

# delete service and this service is not reachable
# but that only delete service, POD still exist
kubectl delete service -l run=kubernetes-bootcamp

# use this command is easy to understand POD still exist
kubectl exec -ti "$POD_NAME" curl localhost:8080

