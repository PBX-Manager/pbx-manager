# PBX-Manager

Management utils for FreePBX.

## How to run docker image

The following mandatory environment variables must be set:

| Environment Variable            | Description                                                   |
|---------------------------------|---------------------------------------------------------------|
| POSTGRES_HOST                   | Host where PostgreSQL is running                              |
| POSTGRES_DB                     | Database name configured for PBX-Manager                      |
| POSTGRES_USER                   | Username configured for PBX-Manager                           |
| POSTGRES_PASS                   | Password of the PBX-Manager user                              |
| TELLOWS_API_KEY                 | API key for the Tellows service                               |
| TELLOWS_COUNTRY                 | Country for which to check numbers without international code |
| TELLOWS_LANGUAGE                | Language of the response from Tellows                         |
| FREEPBX_GRAPHQL_URL             | GraphQL endpoint of your FreePBX instance                     |
| FREEPBX_OAUTH_CLIENT_ID         | Client ID obtained for PBX-Manager                            |
| FREEPBX_OAUTH_CLIENT_SECRET     | Client secret obtained for PBX-Manager                        |
| FREEPBX_OAUTH_AUTHORIZATION_URL | OAuth 2 authorization endpoint of your FreePBX instance       |
| FREEPBX_OAUTH_TOKEN_URL         | OAuth 2 token endpoint of your FreePBX instance               |
| ALLOWED_ORIGINS                 | URLs of clients that can access the backend (CORS header)     |

The following environment variable must be set if the PostgreSQL host is not running on the default port:

| Environment Variable | Description                      |
|----------------------|----------------------------------|
| POSTGRES_PORT        | Port where PostgreSQL is running |