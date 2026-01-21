FROM oraclelinux:9

ARG ARG_WILDFLY_USERNAME
ARG ARG_WILDFLY_PASSWORD
ARG ARG_WILDFLY_DEBUG_FLAG
ARG ARG_WILDFLY_LINK
ARG ARG_POSTGRES_JDBC_LINK

ENV SYSTEM_USER=wildfly
ENV HOME=/home/$SYSTEM_USER

ENV WILDFLY_ZIP=${ARG_WILDFLY_LINK##*/}
ENV WILDFLY_ZIP_PATH=$HOME/$WILDFLY_ZIP
ENV WILDFLY_PATH=$HOME/${WILDFLY_ZIP%.*}

RUN dnf update -y
RUN dnf install unzip -y
RUN dnf install wget -y
RUN dnf install -y java-17-openjdk.x86_64
RUN dnf install -y java-17-openjdk-devel.x86_64
RUN dnf install -y mc

RUN groupadd --gid 1000 $SYSTEM_USER && \
    useradd --uid 1000                  \
            --gid $SYSTEM_USER          \
            --create-home               \
            --home-dir $HOME            \
            --shell /bin/bash           \
            $SYSTEM_USER
USER $SYSTEM_USER

RUN wget -P $HOME $ARG_WILDFLY_LINK
RUN unzip $WILDFLY_ZIP_PATH -d $HOME
RUN rm $WILDFLY_ZIP_PATH

RUN $WILDFLY_PATH/bin/add-user.sh --user $ARG_WILDFLY_USERNAME --password $ARG_WILDFLY_PASSWORD --realm ManagementRealm

RUN wget -P $HOME $ARG_POSTGRES_JDBC_LINK
RUN $WILDFLY_PATH/bin/jboss-cli.sh \
--command="module add --name=org.postgresql --resources=$HOME/${ARG_POSTGRES_JDBC_LINK##*/} --dependencies=javax.api,javax.transaction.api"

RUN $WILDFLY_PATH/bin/jboss-cli.sh \
--commands="embed-server --server-config=standalone.xml, \
/subsystem=datasources/jdbc-driver=postgres:add(driver-name=postgres,driver-module-name=org.postgresql,driver-class-name=org.postgresql.Driver), \
stop-embedded-server"

ENV WILDFLY_DEBUG_FLAG=$ARG_WILDFLY_DEBUG_FLAG
CMD ["sh", "-c", "$WILDFLY_PATH/bin/standalone.sh $WILDFLY_DEBUG_FLAG -c=standalone.xml -b=0.0.0.0 -bmanagement=0.0.0.0"]
