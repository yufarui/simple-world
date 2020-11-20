# Performing a Rolling Update
# https://kubernetes.io/docs/tutorials/kubernetes-basics/update/update-intro/

# update deployments image
kubectl set image deployments kubernetes-bootcamp
kubernetes-bootcamp=jocatalin/kubernetes-bootcamp:v2

# confirm the update
kubectl rollout status deployments/kubernetes-bootcamp

# update deployments image again
kubectl set image deployments/kubernetes-bootcamp
kubernetes-bootcamp=gcr.io/google-samples/kubernetes-bootcamp:v10

# rollout undo
# The rollout command reverted the deployment to the previous known state
kubectl rollout undo deployments/kubernetes-bootcamp

