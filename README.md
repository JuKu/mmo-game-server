# mmo-game-server
A game server (tooling) for a mmo game. This is only a tool, not a complete game!

[![Build Status](https://travis-ci.org/JuKu/mmo-game-server.svg?branch=master)](https://travis-ci.org/JuKu/mmo-game-server)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=com.jukusoft%3Ammo-game-server&metric=ncloc)](https://sonarcloud.io/dashboard/index/com.jukusoft%3Ammo-game-server)
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2FJuKu%2Fmmo-game-server.svg?type=shield)](https://app.fossa.io/projects/git%2Bgithub.com%2FJuKu%2Fmmo-game-server?ref=badge_shield)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=com.jukusoft%3Ammo-game-server&metric=alert_status)](https://sonarcloud.io/dashboard/index/com.jukusoft%3Ammo-game-server) 
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.jukusoft%3Ammo-game-server&metric=coverage)](https://sonarcloud.io/dashboard/index/com.jukusoft%3Ammo-game-server) 
[![Technical Debt Rating](https://sonarcloud.io/api/project_badges/measure?project=com.jukusoft%3Ammo-game-server&metric=sqale_index)](https://sonarcloud.io/dashboard/index/com.jukusoft%3Ammo-game-server) 
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=com.jukusoft%3Ammo-game-server&metric=code_smells)](https://sonarcloud.io/dashboard/index/com.jukusoft%3Ammo-game-server) 
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=com.jukusoft%3Ammo-game-server&metric=bugs)](https://sonarcloud.io/dashboard/index/com.jukusoft%3Ammo-game-server) 
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=com.jukusoft%3Ammo-game-server&metric=vulnerabilities)](https://sonarcloud.io/dashboard/index/com.jukusoft%3Ammo-game-server) 
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=com.jukusoft%3Ammo-game-server&metric=security_rating)](https://sonarcloud.io/dashboard/index/com.jukusoft%3Ammo-game-server) 

[![Sonarcloud](https://sonarcloud.io/api/project_badges/quality_gate?project=com.jukusoft%3Ammo-game-server)](https://sonarcloud.io/dashboard/index/com.jukusoft%3Ammo-game-server)

## Requirements

  - LDAP Server (with kerberos)
  - [MySQL Server](https://www.mysql.com/de/)
  - [Hazelcast Server](http://hazelcast.org)
  - FTP Server (stores maps and map data)
  
See also [MMO Proxy Server](https://github.com/JuKu/mmo-proxy-server)

## Network Protocol

Network Protocol is specified [here](https://github.com/JuKu/mmo-proxy-server) (proxy is transparent).

## Modules

  - Core (Gameplay Mechanics, Player Synchronisation, ID Generation, Entities)
  - Frontend (TCP game frontend to proxy server)
  - Backend
  - Commons (used for proxy, gameserver and all other mmo specific things, including client)
  - Database (persistence layer)
  - Main (main configuration)
  
## Server Architecture

See [MMO Proxy Server](https://github.com/JuKu/mmo-proxy-server).

## License
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2FJuKu%2Fmmo-game-server.svg?type=large)](https://app.fossa.io/projects/git%2Bgithub.com%2FJuKu%2Fmmo-game-server?ref=badge_large)