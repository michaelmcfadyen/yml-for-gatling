## Yaml For Gatling

### Goals
Provide an entrypoint for developers who want to use Gatling without requiring any scala knowledge. 

#### NOTE
This library only provides basic Gatling features through yml config files by design. If you want to use more 
advanced features, do not use this library.  

### How To Run
See [yaml-for-gatling demo](https://github.com/michaelmcfadyen/yml-for-gatling-demo)

### Yml Config Format
One yml config file represents one simulation. The object contains the following fields:

- title (String) - name of the simulation
- maxDuration (Duration) - the max length of the simulation
- ramp (Object) - ramp settings of the simulation
- scenarions (List<Object>) - one or more scenario definitions for your simulation

#### Ramp Section
The ramp section of the config has the following fields:

- enabled (boolean) - whether or not to ramp up at the start of the simulation
- duration (Duration) - if enabled, the length of time to ramp at the start of the simulation

#### Scenario Section
The scenario section of the config defines the behaviour a user will execute during the simulation. It has the 
following fields:

- title (String) - Name of the scenario
- endpoint (String) - endpoint which will be hit. Can include templated values if a feeder is used
- method (GET/PUT/POST/DELETE) - http method to be used
- targetTps (int) - transactions per second of scenario
- feeder (List<Object>) - one or more feeder definitions

#### Feeder Section
The feeder section of the config defines a standard Gatling feeder to use. It has the following fields:

- type (csv/ssv/tsv) - the type of feeder
- file (String) - the filename on the classpath to load data from

### Example yaml file
```yaml
    title: Yaml For Gatling Test
    ramp:
      enabled: true
      duration: PT1M
    scenarios:
      - title: Test GET scenario
        endpoint: /user
        method: GET
        targetTps: 10
      - title: Test GET one user
        endpoint: /user/1
        method: GET
        targetTps: 10
    
    maxDuration: PT1M
```    
### Example yaml file with ramp disabled
```yaml
    title: Yaml For Gatling Test
    ramp:
      enabled: false
    scenarios:
      - title: Test GET scenario
        endpoint: /user
        method: GET
        targetTps: 10
      - title: Test GET one user
        endpoint: /user/1
        method: GET
        targetTps: 10
    
    maxDuration: PT1M
```    

### Example yaml file with feeder
```yaml
title: Yaml For Gatling Test With Feeder
ramp:
  enabled: false
scenarios:
  - title: Test GET one user
    endpoint: /user/${value}
    method: GET
    targetTps: 10
    feeder:
      - type: csv
        file: example.csv
    maxDuration: PT1M
```    