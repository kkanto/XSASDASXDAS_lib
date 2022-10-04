
      package de.allianz

      def lib = library identifier: 'BizDevOps_JSL@develop', retriever: modernSCM(
      [\$class: 'GitSCMSource',
        remote: 'https://github.developer.allianz.io/JEQP/BizDevOps-JSL.git',
        credentialsId: 'git-token-credentials'])

      def jslMaven      = lib.de.allianz.bdo.pipeline.JSLMaven.new()
      def jslGradle     = lib.de.allianz.bdo.pipeline.JSLGradle.new()
      def jslNexus      = lib.de.allianz.bdo.pipeline.JSLNexus.new()
      def jslSonarqube  = lib.de.allianz.bdo.pipeline.JSLSonarqube.new()

      def build() {
        jslMaven.build()
      }

      def componentTest {
        jslMaven.testunit("component") 
      }

      def integrationTest {
        jslMaven.testunit("integration")
      }

      def uatTest {
        jslMaven.testunit("uat")
      }

      def acceptanceTest {
        jslMaven.testunit("acceptance")
      }

      def publishArtifacts() {
        jslNexus.push()
      }
    