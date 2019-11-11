# PulsaraPatientSync
Jason Jerome Candidate Project - Patient Sync


## Setup

* You **must** know the IP Address of the computer where the endpoint is running, otherwise the app will not find it. (**`localhost` will not work**)

* Set the endpoint URL in the **project-level** `.gradle_properties` file (see section below)

* Make sure you're running the server :)

## Project Properties

The URL for the server endpoint is set in the _project level_ `.gradle_properties` file.

If the `PatientEndpointUrl` is not set, gradle will build the project using a test url. In this case the app will use a local json file as a mocked result.

Example of the `gradle.properties` file:
```
PatientEndpointUrl="http://[your-ip-address-here]:8000/api/"
```
