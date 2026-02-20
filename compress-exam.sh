#!/bin/bash -v

zip -v -r POS_exam_dotnet.zip POS_exam_dotnet/ \
  -x "POS_exam_dotnet/**/target/**" "POS_exam_dotnet/.idea/**" "POS_exam_dotnet/.git/**" "POS_exam_dotnet/**/bin/**" "POS_exam_dotnet/**/obj/**"

zip -v -r POS_exam_java.zip POS_exam_java/ \
  -x "POS_exam_java/**/target/**" "POS_exam_java/.idea/**" "POS_exam_java/.git/**"
