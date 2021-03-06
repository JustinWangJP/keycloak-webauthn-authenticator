# Keycloak WebAuthn Authenticator

[![Build Status](https://travis-ci.org/webauthn4j/keycloak-webauthn-authenticator.svg?branch=master)](https://travis-ci.org/webauthn4j/keycloak-webauthn-authenticator)
[![license](https://img.shields.io/github/license/webauthn4j/keycloak-webauthn-authenticator.svg)](https://github.com/webauthn4j/keycloak-webauthn-authenticator/blob/master/LICENSE)

[Web Authentication](https://www.w3.org/TR/webauthn/)(WebAuthn) sample plugin for [Keycloak](https://www.keycloak.org) , implements with [webauthn4j](https://github.com/webauthn4j/webauthn4j).

## Environment

We've confirmed that this demo had worked well under the following environments:

- 2 Factor Authentication with Resident Key Not supported Authenticator Scenario

  - OS : Windows 10
  - Browser : Google Chrome (ver 73), Mozilla FireFox (ver 66)
  - Authenticator : Yubico Security Key
  - Server(RP) : keycloak-5.0.0 on localhost

- 2 Factor Authentication with Resident Key Not supported Authenticator Scenario

  - OS : macOS OS Mojave (ver 10.14.3)
  - Browser : Google Chrome (ver 73), Mozilla FireFox (ver 66)
  - Authenticator : Yubico Security Key
  - Server(RP) : keycloak-5.0.0 on localhost

- 2 Factor Authentication with Resident Key supported Authenticator Scenario

  - OS : Windows 10
  - Browser : Microsoft Edge (ver 44)
  - Authenticator : Internal Fingerprint Authentication Device
  - Server(RP) : keycloak-5.0.0 on localhost

- Authentication with Resident Key supported Authenticator Scenario

  - OS : Windows 10
  - Browser : Microsoft Edge (ver 44)
  - Authenticator : Internal Fingerprint Authentication Device
  - Server(RP) : keycloak-5.0.0 on localhost

## Install

- Build:

  - `$ mvn install`

- Add the EAR file to the Keycloak Server:

  - `$ cp webuahtn4j-ear/target/keycloak-webauthn4j-ear-*.ear $KEYCLOAK_HOME/standalone/deployment/`

- Or deploy the EAR file dynamically when the Keycloak Server:

  - `$ mvn clean install wildfly:deploy`

- Report coverage

  - `$ mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent test`
  - `$ mvn org.jacoco:jacoco-maven-plugin:report`

## Overview

This prototype consists of two components:

- WebAuthn Register

This enable users to register their accounts on keycloak with their authenticators' generating public key credentials. It is implemented as `Required Action`.

- WebAuthn Authenticator

This enable users to authenticate themselves on keycloak by their authenticators. It is implemented as `Authenticaor`.

## Realm Settings

To enable user without their accounts on keycloak to register them on the authentication flow:

- Enable `User registration` in 'Realm Settings' - 'Login'

## Authentication Required Actions Settings

To enable users to register their accounts with their authenticators' creating public key credentials:

-  register `Webauthn Register` Required Action in 'Required Actons' - 'Register'

-  check `Enabled` and `Default Action` for registered `Webauthn Register` Required Action


## Authentication Flow Settings

To enable users having their accounts on keycloak to authenticate themselves on keycloak by their authenticators:

### Browser Flow (2 Factor Authentication)

| Auth Type                    |                        | Requirement |
| ---------------------------- | ---------------------- | ----------- |
| Cookie                       |                        | ALTERNATIVE |
| Kerberos                     |                        | DISABLED    |
| Identity Provider Redirector |                        | ALTERNATIVE |
| Copy of Browser Flow         |                        | ALTERNATIVE |
|                              | Username Password Form | REQUIRED    |
|                              | OTP Form               | OPTIONAL    |
|                              | WebAuthn Authenticator | REQUIRED    |

### Browser Flow (Use `Resident Key`)

| Auth Type                    |     | Requirement |
| ---------------------------- | --- | ----------- |
| Cookie                       |     | ALTERNATIVE |
| Kerberos                     |     | DISABLED    |
| Identity Provider Redirector |     | ALTERNATIVE |
| WebAuthn Authenticator       |     | REQUIRED    |

### Notes

Browser Flow (Use `Resident Key`) automatically asks users to authenticate on their authenticators. Therefore, the users without their accounts have no chance to register them on this flow.

For such the users to register their accounts, please use the default Browser Flow. It is helpful to user `Authentication Flow Overrides` on Client Settings. You can set the default Browser Flow for User Accont Service (Client ID: account) to let users register their accounts at first.

## TODO

- [x] [credential storage : avoid creating a new table for credentials](https://github.com/webauthn4j/keycloak-webauthn-authenticator/issues/7)
- [x] [webauthn4j 0.9.2.RELEASE support](https://github.com/webauthn4j/keycloak-webauthn-authenticator/issues/8)
- [x] [Unit Test](https://github.com/webauthn4j/keycloak-webauthn-authenticator/issues/13)
- [ ] CI Integration

_TBD_
