# sunbird-user-service
Welcome to Sunbird user-org service

## Getting started
This service depends on user-registry maintained in a separate repo [here](https://github.com/project-sunbird/sunbird-user-registry.git). To build user-org service, user-registry must be added as a git submodule.
 
## Steps to build and run
2. Go the project folder

> cd sunbird-user-service

3. Setup git module

> git submodule -b <release_name> add https://github.com/project-sunbird/sunbird-user-registry.git sunbird-user-registry

where <release_name> is typically the branch specified in the .gitmodules file.

4. Update the gitmodule

> git submodule update --init --recursive --remote

5. Build
The user-registry is packaged with the necessities of this service. One can override those values, by running setupVars.sh script.

> cd sunbird-user-registry 

> ./sb-registry-configure-dependencies.sh

> cd ..

> mvn clean install

5.1 Set vars (optional)
Make your own copy of setVars.sh.sample provided in this repo and let's call it setVars.sh. You will have to set execute permissions in unix environments.
>  ./setVars.sh

6. Run

> cd user-org-service

Debug mode
> mvn play2:run

Detached mode
> mvn play2:start

### How to ensure the service is running

Query the health status using the following curl command.

` curl -X GET \
    http://localhost:9000/health \
    -H 'Postman-Token: 5a18db13-553c-42e6-9580-ebe2559646ad' \
    -H 'cache-control: no-cache'    
`

Success response will be like 

`{"id":"api.user.apiId",
  "ver":"v1",
  "ts":"2019-01-17 16:53:26:286+0530",
  "params":{
      "resmsgid":null,
      "msgid":"8e27cbf5-e299-43b0-bca7-8347f7ejk5abcf",
      "err":null,
      "status":"success",
      "errmsg":null
  },
  "responseCode":"OK",
  "result":{
     "response":{
        "response":"SUCCESS",
        "errors":[]
     }
  }
}`