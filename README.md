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
Make your own copy of setVars.sh.sample provided in this repo and let's call it setVars.sh
>  ./setVars.sh

6. Debug

> mvn play2:run

7. Run (detached mode)

> mvn play2:start

### Ensure the project is running

> 