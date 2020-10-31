Feature: the resource features of Equipment


  Background:
    When user authenticate with password "test"
    Then the response status code should be 200

  # get all equipments
  Scenario:User calls a rest endpoint to get all Equipments

    #save the following equipment in the database
    Given the following equipments
      | equipment id | valve         | actuator         | postioner | sensor          |
      | 22Y2071      | 22Y2071-valve | 22Y2071-actuator | 22Y2071   | V022 AB22 Y2071 |
      | 22Y5090      | 22Y5090-valve | 22Y5090-actuator | 22Y5090   | V022 AB22 Y5090 |


    When user sends GET request to endpoint /equipment/ with content-type application/json and empty headers
    Then the response status code should be 200
    And the json response has "equipments" object
    And the "equipments" list size should be 2



  #get details about exist equipment by equipment_id
  Scenario:User calls a rest endpoint to get Details about an equipment according to given equipment_id

    #save the following equipment in the database
    Given the following equipments
      | equipment_id | valve           | actuator           | postioner | sensor          |
      | 22Y5090      | 22Y5090-valve   | 22Y5090-actuator   | 22Y5090   | V022 AB22 Y5090 |

    When user sends GET request to endpoint /equipment/22Y5090 with content-type application/json and empty headers
    Then the response status code should be 200
    And the json response has "equipment" object
    And the json response of the "equipment" should be like that
      | equipment_id | 22Y5090          |
      | valve        | 22Y5090-valve    |
      | actuator     | 22Y5090-actuator |
      | postioner    | 22Y5090          |
      | sensor       | V022 AB22 Y5090  |



  #get details about non exist equipment
  Scenario:User calls a rest endpoint to get Details about non exist equipment

    When user sends GET request to endpoint /equipment/225522u1 with content-type application/json and empty headers
    Then the response status code should be 404
    And the json response has "apiError" object
    And the json response of the "apiError" should be like that
      | errorNumber  | APP1-EQUIPMENT-ERROR-001                     |
      | errorMessage | the equipment whose id is 225522u1 not found |



  #delete an equipment
  Scenario:User calls a rest endpoint to delete an equipment according to given equipment_id
    #save the following equipment in the database
    Given the following equipments
      | equipment_id | valve           | actuator           | postioner | sensor          |
      | 22Y2071      | 22Y2071-valve   | 22Y2071-actuator   | 22Y2071   | V022 AB22 Y2071 |
    When user sends DELETE request to endpoint /equipment/22Y2071 with content-type application/json and empty headers
    Then the response status code should be 204




  #delete non exist equipment
  Scenario:User calls a rest endpoint to delete non exist equipment

    When user sends DELETE request to endpoint /equipment/225522u1 with content-type application/json and empty headers
    Then the response status code should be 404
    And the json response has "apiError" object
    And the json response of the "apiError" should be like that
      | errorNumber  | APP1-EQUIPMENT-ERROR-001                     |
      | errorMessage | the equipment whose id is 225522u1 not found |





  #update non exist equipment according to equipment_id
  Scenario:User calls a rest endpoint to update non exist equipment

    #save the following equipment in the database
    Given the following equipments
      | equipment_id | valve           | actuator           | postioner | sensor          |
      | 22Y2071      | 22Y2071-valve   | 22Y2071-actuator   | 22Y2071   | V022 AB22 Y2071 |

    When user sends PUT request to endpoint /equipment/225522u1 with content-type application/json and empty headers and body {"valve": "test", "actuator" :"test","postioner":"test","sensor":"test"}
    Then the response status code should be 404
    And the json response has "apiError" object
    And the json response of the "apiError" should be like that
      | errorNumber  | APP1-EQUIPMENT-ERROR-001                     |
      | errorMessage | the equipment whose id is 225522u1 not found |


  #update an equipment according to equipment_id
  Scenario:User calls a rest endpoint to update an equipment according to given equipment_id
    #save the following equipment in the database
    Given the following equipments
      | equipment_id | valve           | actuator           | postioner | sensor          |
      | 22Y2071      | 22Y2071-valve   | 22Y2071-actuator   | 22Y2071   | V022 AB22 Y2071 |

    When user sends PUT request to endpoint /equipment/22Y2071 with content-type application/json and empty headers and body {"valve": "test", "actuator" :"test","postioner":"test","sensor":"test"}
    Then the response status code should be 201


    #check if the update has been done
    When user sends GET request to endpoint /equipment/22Y2071 with content-type application/json and empty headers
    Then the response status code should be 200
    And the json response has "equipment" object
    And the json response of the "equipment" should be like that
      | equipment_id | 22Y2071 |
      | valve        | test    |
      | actuator     | test    |
      | postioner    | test    |
      | sensor       | test    |


  #update an equipment according to equipment_id with invalid format
  Scenario:User calls a rest endpoint to update an equipment according to given equipment_id ith invalid format

    #save the following equipment in the database
    Given the following equipments
      | equipment_id | valve           | actuator           | postioner | sensor          |
      | 22Y2071      | 22Y2071-valve   | 22Y2071-actuator   | 22Y2071   | V022 AB22 Y2071 |

    When user sends PUT request to endpoint /equipment/22Y2071 with content-type application/json and empty headers and body {"valve": "test", "actuator" :,"postioner":"test","sensor":"test"}
    Then the response status code should be 400


  #create new equipment
  Scenario:User calls a rest endpoint to create new equipment

    When user sends POST request to endpoint /equipment/ with content-type application/json and empty headers and body {"equipment_id":"news","valve":"news","actuator" :"news","postioner":"news","sensor":"news"}
    Then the response status code should be 201


    #check if the creation has been done
    When user sends GET request to endpoint /equipment/news with content-type application/json and empty headers
    Then the response status code should be 200
    And the json response has "equipment" object
    And the json response of the "equipment" should be like that
      | equipment_id | news |
      | valve        | news |
      | actuator     | news |
      | postioner    | news |
      | sensor       | news |





  #create new equipment with invalid format
  Scenario:User calls a rest endpoint to create new equipment with invalid format

    When user sends POST request to endpoint /equipment/ with content-type application/json and empty headers and body {"equipment_id":"2w2Y2071","valve":"new","actuator" :,"postioner":"new","sensor":"new"}
    Then the response status code should be 400


  #create an already exist equipment
  Scenario:User calls a rest endpoint to create already exist equipment
    Given the following equipments
      | equipment_id | valve           | actuator           | postioner | sensor          |
      | 22Y2071      | 22Y2071-valve   | 22Y2071-actuator   | 22Y2071   | V022 AB22 Y2071 |

    When user sends POST request to endpoint /equipment/ with content-type application/json and empty headers and body {"equipment_id":"22Y2071","valve":"2Y2071-valve","actuator" :"22Y2071-actuator","postioner":"22Y2071","sensor":"V022 AB22 Y2071"}
    Then the response status code should be 403
    And the json response has "apiError" object
    And the json response of the "apiError" should be like that
      | errorNumber  | APP1-EQUIPMENT-ERROR-003                        |
      | errorMessage | the equipment whose id is 22Y2071 already exist |