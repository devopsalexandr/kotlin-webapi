import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.10"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("org.jetbrains.kotlin.plugin.noarg") version "1.5.30"
	id("org.jetbrains.kotlin.plugin.jpa") version "1.5.30"
	kotlin("jvm") version "1.4.32"
	kotlin("plugin.spring") version "1.4.32"
	kotlin("plugin.allopen") version "1.4.32"
}

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.Embeddable")
	annotation("javax.persistence.MappedSuperclass")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.github.mvallim:java-fluent-validator:1.9.3")
	implementation("org.hibernate.validator:hibernate-validator:7.0.1.Final")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.5.4")
	implementation("mysql:mysql-connector-java:8.0.25")
	implementation("org.liquibase:liquibase-core:4.4.3")

	implementation("io.jsonwebtoken:jjwt-impl:0.11.2")
	implementation("io.jsonwebtoken:jjwt-jackson:0.11.2")
	implementation("io.jsonwebtoken:jjwt-api:0.11.2")
	implementation("org.jetbrains.kotlin:kotlin-noarg:1.5.21")
	implementation("org.springframework.security:spring-security-crypto:5.5.1")
	implementation("org.springframework.security:spring-security-core:5.5.1")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

noArg {
	invokeInitializers = true
}