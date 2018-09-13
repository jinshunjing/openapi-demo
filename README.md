# openapidemo
Open API Demo built on Spring Security OAuth

1. Run SecretToolTest to generate the client id, secret and encoded secret
2. Get the access token at first, such as <br/>
curl -X POST -F 'grant_type=client_credentials' -F 'scope=trust' -F 'client_id=645968e2bad447d78286724a135fa868' -F 'client_secret=61ccdbce2e15ee92c55019d4406eca38f2fb6b92' http://127.0.0.1:18002/oauth/token
3. Then provide the access token to call the open API <br/>
http://127.0.0.1:18002/v1/user/greeting?access_token=3e04896f-b24a-43cb-942b-c95ca403c69a <br/>
curl -X POST -F 'access_token=bba375ad-8707-4e31-9963-bf102c035f2d' http://127.0.0.1:18002/v1/user/bye

Note: POST must use Form Authentication
