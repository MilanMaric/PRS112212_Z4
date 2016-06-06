//
// Created by milan on 5.6.2016.
//

#include "net_etfbl_prs_prs112212_z4_Hash.h"
#include <stdio.h>


JNIEXPORT jlong JNICALL Java_net_etfbl_prs_prs112212_1z4_Hash_getHash(JNIEnv *env, jobject obj, jstring str){
    const char *chars=(*env)->GetStringUTFChars(env, str, 0);
    int i=0;
    long hash=0;
    while (chars[i]!='\0'){
        hash = chars[i] + (hash << 6) + (hash << 16) - hash;
        i++;
    }
    return hash;
}

