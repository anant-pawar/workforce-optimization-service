$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/test.feature");
formatter.feature({
  "name": "Is it Friday yet",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Sunday isn\u0027t Friday",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "I ask whether it\u0027s Friday yet",
  "keyword": "When "
});
formatter.match({
  "location": "StepDef.i_ask_whether_it_s_Friday_yet()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I should be told \"Nope\"",
  "keyword": "Then "
});
formatter.match({
  "location": "StepDef.i_should_be_told(String)"
});
formatter.result({
  "status": "passed"
});
});