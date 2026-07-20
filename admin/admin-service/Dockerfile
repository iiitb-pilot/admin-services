FROM eclipse-temurin:21-jre

ARG SOURCE
ARG COMMIT_HASH
ARG COMMIT_ID
ARG BUILD_TIME
LABEL source=${SOURCE}
LABEL commit_hash=${COMMIT_HASH}
LABEL commit_id=${COMMIT_ID}
LABEL build_time=${BUILD_TIME}

ARG spring_config_label
ARG active_profile
ARG spring_config_url
ARG artifactory_url
ARG is_glowroot
ARG iam_adapter_url

ENV active_profile_env=${active_profile}
ENV spring_config_label_env=${spring_config_label}
ENV spring_config_url_env=${spring_config_url}
ENV is_glowroot_env=${is_glowroot}
ENV artifactory_url_env=${artifactory_url}
ENV iam_adapter_url_env=${iam_adapter_url}

ARG container_user=mosip
ARG container_user_group=mosip
ARG container_user_uid=1002
ARG container_user_gid=1001

# Install required packages and create user
RUN apt-get update \
 && apt-get install -y unzip wget \
 && rm -rf /var/lib/apt/lists/* \
 && groupadd -g ${container_user_gid} ${container_user_group} \
 && useradd -u ${container_user_uid} \
    -g ${container_user_group} \
    -m \
    -s /bin/sh \
    ${container_user}

WORKDIR /home/${container_user}

ENV work_dir=/home/${container_user}

ARG loader_path=${work_dir}/additional_jars/

RUN mkdir -p ${loader_path}

ENV loader_path_env=${loader_path}

COPY ./target/admin-service-*.jar admin-service.jar

RUN chown -R ${container_user}:${container_user} /home/${container_user}

USER ${container_user_uid}:${container_user_gid}

EXPOSE 8098

CMD wget -q --show-progress "${iam_adapter_url_env}" -O "${loader_path_env}/kernel-auth-adapter.jar"; \
    java \
    -XX:-UseG1GC \
    -XX:+ExplicitGCInvokesConcurrent \
    -XX:-UseParallelGC \
    -XX:+UseZGC \
    -XX:+ZGenerational \
    -XX:-UseShenandoahGC \
    -XX:MaxGCPauseMillis=200 \
    -XX:+UnlockExperimentalVMOptions \
    -XX:+UseStringDeduplication \
    -XX:+HeapDumpOnOutOfMemoryError \
    -Dfile.encoding=UTF-8 \
    -XX:+UseCompressedOops \
    -Dloader.path="${loader_path_env}" \
    --add-modules=ALL-SYSTEM \
    --add-opens=java.base/java.lang=ALL-UNNAMED \
    -Dspring.cloud.config.label="${spring_config_label_env}" \
    -Dspring.profiles.active="${active_profile_env}" \
    -Dspring.cloud.config.uri="${spring_config_url_env}" \
    -jar admin-service.jar