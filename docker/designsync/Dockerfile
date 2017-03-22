# DockerFile for installing and launching a DesignSync server

# Needs to be a platform that is supported by DesignSync
FROM centos:centos7

MAINTAINER Bruce Schurmann <Bruce.Schurmann@3ds.com>

# Add the EPEL repository to allow installing other items below
RUN yum -y install wget
RUN wget https://dl.fedoraproject.org/pub/epel/epel-release-latest-7.noarch.rpm
RUN rpm -ivh epel-release-latest-7.noarch.rpm

# Install all the bits and pieces that are required for the installation, configuration
# and running of DesignSync
RUN yum -y install openssh-clients
RUN yum -y install sshpass
RUN yum -y install tcl
RUN yum -y install expect
RUN yum -y install ksh
RUN yum -y install redhat-lsb-core
RUN yum -y install which
#RUN yum -y install bind-utils
# need this for the installation process (selfreg, ...)
RUN yum -y install tcsh

# Create the user that will own the installation
RUN useradd -ms /bin/bash e4ainst

# Now switch to the new user for all the steps below
USER e4ainst
WORKDIR /home/e4ainst

# Prep the user space to enable the 'scp' below
RUN mkdir .ssh
RUN chmod 700 .ssh
RUN echo "|1|W4c9gk0bMyiwCH1vK5KeUcQh9m8=|o3y3zEtVHfVDfn+iacS21FKeU1k= ssh-rsa AAAAB3NzaC1yc2EAAAABIwAAAQEAuT2fgsB6efqppuAAVMqi3qXbE549iLkM8FAOLBjE2XKUK6R5fjf0YBCXlDhwN9h+w3MQf1NmnGgd9nRIoU9XJHufAfhFJkNQgQY4RMFG0nDFvmnjZXu3bgWChL91e7SD+gTLQIUY4lNagOs+iqoDvTPrqGb2JuWPZiDsGLyMvIPHsSWzW5qi3tXgpPPgMLSkGDozCpRQ2sbuwdZdOqplAfSdAtNlmOJuO8IdcGgcFHytBb3Y3EqLQk65a2foivd2DIyKUmKm61Ks85g3ig6GOTYGDfG02x6PMvDP0mYhxGu8oDDiKUchZ6KI2fp0h1jtrfPXMDgdB4LSGyDEW7Aytw==" > .ssh/known_hosts
RUN chmod 644 .ssh/known_hosts

# Grab all the bits and the installation bundle
RUN sshpass -p "cre999z" scp bruces@192.168.0.1:/home/bruces/Download/Synchronicity/syncinc_r420_root.tar.gz .

# Untar all the bits
RUN tar -zxpf syncinc_r420_root.tar.gz

# Cleanup so the image is not bloated
RUN rm syncinc_r420_root.tar.gz

# Setup the env
ENV HOME /home/e4ainst
ENV USER e4ainst
ENV SYNC_DIR_HOME ${HOME}/syncinc_r420
ENV SYNC_DIR ${SYNC_DIR_HOME}/linux_a64
ENV PATH "${SYNC_DIR}/bin:${PATH}"
# This env var addresses the BIG issue related to hostname during 'docker build' and 'docker run'
ENV SYNC_HOSTNAME=ds_docker_host

# Last step before installation
RUN mkdir -p ${SYNC_DIR_HOME}

# Need to make the server port available outside of the container
EXPOSE 30080

# Install and configure
RUN ${HOME}/build/R420/src/test/testir/t_install  -t_master_file ${HOME}/etc/t_install.r420.cfg \
                     -t_install_program "/bin/bash ${HOME}/build/R420/src/build_tools/cbe_build_test -i -b R420 -d ${HOME}" \
                     -v6_install_path $SYNC_DIR_HOME \
                     -server_admin_email Bruce.Schurmann@3ds.com \
                     -server_mas y -server_rs y  \
                     -server_postgres_password  123456  \
                     -server_postgres_port      50080  \
                     -server_port               30080  \
                     -server_ssl_port           30081  \
                     -server_vault              ${SYNC_DIR_HOME}/syncdata/${HOSTNAME}/30080/server_vault  \
                     -server_metadata           ${SYNC_DIR_HOME}/syncdata/${HOSTNAME}/30080/server_metadata  \
                     -build_test_which_ac 2

# Remove more bloat
RUN rm -rf Adele
RUN rm -rf build
RUN rm -rf QE
RUN rm -rf pkg.Linux
RUN rm -rf syncinc_inst
RUN rm -rf syncinc_r420/InstallData

# Finally start the server
CMD exec /bin/bash -c "trap : TERM INT; start_sync_server; sleep infinity & wait"

# Built-in healthcheck reflected in 'docker ps'
# May need to disable this if debugging run problems
HEALTHCHECK CMD curl --fail http://localhost:30080/ || exit 1
