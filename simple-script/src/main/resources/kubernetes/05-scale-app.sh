# https://kubernetes.io/docs/tutorials/kubernetes-basics/scale/scale-intro/

kubectl get deployments

:<<EOF
NAME                  READY   UP-TO-DATE   AVAILABLE   AGE
kubernetes-bootcamp   1/1     1            1           29s

deployments intro
NAME lists the names of the Deployments in the cluster.
READY shows the ratio of CURRENT/DESIRED replicas
UP-TO-DATE displays the number of replicas that have been updated to achieve the desired state.
AVAILABLE displays how many replicas of the application are available to your users.
AGE displays the amount of time that the application has been running.
EOF

# To see the ReplicaSet created by the Deployment, use this
kubectl get rs

:<<EOF
NAME                             DESIRED   CURRENT   READY   AGE
kubernetes-bootcamp-765bf4c7b4   1         1         1   4m6s

Name is formatted as [DEPLOYMENT-NAME]-[RANDOM-STRING]

The random string is randomly generated and uses the pod-template-hash as a seed.

DESIRED displays the desired number of replicas of the application,
which you define when you create the Deployment. This is the desired state.
CURRENT displays how many replicas are currently running.
EOF

# scale your app
kubectl scale deployments/kubernetes-bootcamp --replicas=4

kubectl get deployments

:<<EOF
NAME                  READY   UP-TO-DATE   AVAILABLE   AGE
kubernetes-bootcamp   4/4     4            4           25m
EOF

kubectl get pods -o wide

:<<EOF
NAME                                   READY   STATUS    RESTARTS   AGE
kubernetes-bootcamp-765bf4c7b4-b6gv6   1/1     Running   0          27m
kubernetes-bootcamp-765bf4c7b4-q2xdf   1/1     Running   0          3m12s
kubernetes-bootcamp-765bf4c7b4-sf2pb   1/1     Running   0          3m12s
kubernetes-bootcamp-765bf4c7b4-wvs65   1/1     Running   0          3m12s
EOF

:<<EOF
kubectl get services -l run=kubernetes-bootcamp
NAME                  TYPE       CLUSTER-IP      EXTERNAL-IP   PORT(S)          AGE
kubernetes-bootcamp   NodePort   10.97.234.143   <none>        8080:30220/TCP   54m
EOF

# when you call services ip address, you will access to pod by loadbalance
curl 10.97.234.143:8080

# scale down two pods will terminated
kubectl scale deployments/kubernetes-bootcamp --replicas=2