<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <application>

        <#if !isFragment>
            <activity android:name="${packageName}.${className}"
                      android:screenOrientation="portrait"
                      android:windowSoftInputMode="adjustPan">
            </activity>
        </#if>

    </application>

</manifest>