# shuffle-dto Developer Setup Guide

## Introduction

The latest source code is located in the following two public Git repositories

- https://github.com/andybryant/shuffle-android
- https://github.com/andybryant/shuffle-dto

The first is the Android application, the second the model used for backups and sync calls to Shuffle website.

The dto jar uses maven as its build and dependency management tool.

## Prerequisites

1.  Maven 3.x - http://maven.apache.org/download.html
2.  Google Protocol Buffer compiler 2.5.0 - https://github.com/google/protobuf
3.  Set environment variable ```PROTOC_HOME``` to point to the directory holding the protoc executable

## Building

1.  Build protoc tool.
Set ```PROTOC_HOME``` environment variable
Run ```mvn clean install```
