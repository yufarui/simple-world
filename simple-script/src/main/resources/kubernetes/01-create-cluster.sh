# kubernetes basic knowledge
# https://kubernetes.io/zh/docs/tutorials/kubernetes-basics/

# create-cluster
# https://kubernetes.io/zh/docs/tutorials/kubernetes-basics/create-cluster/cluster-intro/

# first create kubernetes-cluster by minikube

# learn by minikube at first
minikube -version

# start kubernetes cluster
minikube start

# to interact with kubernetes we will need kubetcl
# note kubectl can split with `kube` and `ctl` that we can simply remember it
kubetcl -version

# watch the single node cluster info
kubetcl cluster-info

:<<EOF
this is result
Kubernetes master is running at https://172.17.0.39:8443
KubeDNS is running at https://172.17.0.39:8443/api/v1/namespaces/kube-system/services/kube-dns:dns/proxy
EOF

# This command shows all nodes that can be used to host our applications.
# Now we have only one node, and we can see that its status is ready (it is ready to accept applications for deployment).
kubetcl get nodes

:<<EOF
NAME       STATUS   ROLES    AGE   VERSION
minikube   Ready    master   31m   v1.17.0
EOF

# install minikube
# https://minikube.sigs.k8s.io/docs/start/

# downlaod adn install

# curl -L means follow request to redirct
# curl -O means save response as file with request last part as file name
curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64

# install this file to usr dir
sudo install minikube-linux-amd64 /usr/local/bin/minikube

# install fail, the machine don't have enough space,one day i will try it again