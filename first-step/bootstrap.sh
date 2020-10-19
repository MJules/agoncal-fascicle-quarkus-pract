#!/usr/bin/env bash
mvn io.quarkus:quarkus-maven-plugin:1.8.2.Final:create \
    -DprojectGroupId=org.agoncal.fascicle.quarkus-practising \
    -DprojectArtifactId=first-step \
    -DclassName="org.agoncal.fascicle.quarkus.firststep.AuthorResource" \
    -Dpath="/authors"