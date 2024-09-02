job('JavaMavenAppDSL_2') {
    description('Java Maven App con DSL para el curso de Jenkins')
    scm {
        git('https://github.com/macloujulian/simple-java-maven-app.git', 'master') { node ->
            node / gitConfigName('mrhcmx')
            node / gitConfigEmail('mrhcmex@gmail.com')
        }
    }
    steps {
        maven {
          mavenInstallation('mavenJenkins')
          goals('-B -DskipTests clean package')
        }
        maven {
          mavenInstallation('mavenJenkins')
          goals('test')
        }
        shell('''
          echo "Entrega: Desplegando la aplicación" 
          java -jar "/var/jenkins_home/workspace/JavaMavenAppDSL_2/target/my-app-1.0-SNAPSHOT.jar"
        ''')  
    }
    publishers {
        archiveArtifacts('target/*.jar')
        archiveJunit('target/surefire-reports/*.xml')
    }
}
