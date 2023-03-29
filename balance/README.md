# Balance App

## Create Network

<pre>
	# create docker network
	docker network create balance

	# list docker network
	docker network ls
</pre>

## Balance DB

You need to build docker image for Balance DB.

<pre>
	cd balance-db
	docker build -t [username]/balance-db .
</pre>


Running Balance Database Docker Container from Docker Image

<pre>
	# Create Docker Container and Run Container
	docker run -d --network balance \
		--name balance-db \
		-e TZ="Asia/Yangon" \
		minlwin/balance-db

	# List all docker containers
	docker container ls -a	

	# Stop Docker Container
	docker container stop balance-db

	# Start Docker Container again
	docker container start ballance-db

	# Remove Docker Container
	docker container rm balance-db
</pre>

## Accessing Database on Docker Container

<pre>
	# Access to container with b shell
	docker exec -it balance-db bash

	# Using PSQL in Container
	psql -U balance_usr balance_db
</pre>
