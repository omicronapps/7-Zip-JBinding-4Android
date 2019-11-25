#include "SevenZipJBinding.h"

#include "JNITools.h"
#include "CPPToJavaCryptoGetTextPassword.h"
#include "UnicodeHelper.h"

STDMETHODIMP CPPToJavaCryptoGetTextPassword::CryptoGetTextPassword(BSTR * password) {
    TRACE_OBJECT_CALL("CryptoGetTextPassword");

    JNIEnvInstance jniEnvInstance(_jbindingSession);

    if (password) {
        *password = NULL;
    }

    jstring passwordString = (jstring) _iCryptoGetTextPassword->cryptoGetTextPassword(
            jniEnvInstance, _javaImplementation);
    if (jniEnvInstance.exceptionCheck()) {
        return S_FALSE;
    }

    if (password) {
        const jchar * passwordJChars = jniEnvInstance->GetStringChars(passwordString, NULL);
        //CMyComBSTR passwordBSTR((OLECHAR*)(const wchar_t*)UnicodeHelper(passwordJChars));
        //CMyComBSTR passwordBSTR(L"TestXXX");

        //printf("PASSWORD: '%S'\n", (BSTR)passwordBSTR);
        //fflush(stdout);
#ifdef __ANDROID_API__
        StringToBstr(UString(UnicodeHelper(passwordJChars, jniEnvInstance->GetStringLength(passwordString))), password);//passwordBSTR.MyCopy();
#else
        StringToBstr(UString(UnicodeHelper(passwordJChars)), password);//passwordBSTR.MyCopy();
#endif
        jniEnvInstance->ReleaseStringChars(passwordString, passwordJChars);
    }

    if (passwordString) {
        jniEnvInstance->DeleteLocalRef(passwordString);
    }

    return S_OK;
}
