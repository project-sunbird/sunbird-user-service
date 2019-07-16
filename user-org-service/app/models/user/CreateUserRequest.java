package models.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author anmolgupta
 * @desc POJO class for User Create
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class CreateUserRequest implements Serializable {

    private static final long serialVersionUID = -1L;

    private String id;
    private String avatar;
    private String countryCode;
    private String dob;
    private String email;
    private Boolean emailVerified;
    private String firstName;
    private String gender;
    private List<String> grade;
    private List<String> language;
    private String lastName;
    private String location;
    private String phone;
    private Boolean phoneVerified;
    private String profileSummary;
    private Map<String, String> profileVisibility;
    private String provider;
    private List<String> roles;
    private String rootOrgId;
    private List<String> subject;
    private String thumbnail;
    private String userId;
    private String userName;
    private List<Map<String, String>> webPages;
    private String channel;
    private String organisationId;
    private List<Map<String, String>> externalIds;
    private String userType;
    private String tncAcceptedVersion;
    private Map<String, List<String>> framework;
    private List<String> locationIds;
    private List<JobProfile> jobProfile;
    private List<Education> education;
    private List<Address> address;

    public List<String> getLocationIds() {
        return locationIds;
    }

    public void setLocationIds(List<String> locationIds) {
        this.locationIds = locationIds;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(String organisationId) {
        this.organisationId = organisationId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getGrade() {
        return grade;
    }

    public void setGrade(List<String> grade) {
        this.grade = grade;
    }

    public List<String> getLanguage() {
        return language;
    }

    public void setLanguage(List<String> language) {
        this.language = language;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfileSummary() {
        return profileSummary;
    }

    public void setProfileSummary(String profileSummary) {
        this.profileSummary = profileSummary;
    }

    public Map<String, String> getProfileVisibility() {
        return profileVisibility;
    }

    public void setProfileVisibility(Map<String, String> profileVisibility) {
        this.profileVisibility = profileVisibility;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getRootOrgId() {
        return rootOrgId;
    }

    public void setRootOrgId(String rootOrgId) {
        this.rootOrgId = rootOrgId;
    }

    public List<String> getSubject() {
        return subject;
    }

    public void setSubject(List<String> subject) {
        this.subject = subject;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Map<String, String>> getWebPages() {
        return webPages;
    }

    public void setWebPages(List<Map<String, String>> webPages) {
        this.webPages = webPages;
    }

    public List<Map<String, String>> getExternalIds() {
        return externalIds;
    }

    public void setExternalIds(List<Map<String, String>> externalIds) {
        this.externalIds = externalIds;
    }

    public Map<String, List<String>> getFramework() {
        return framework;
    }

    public void setFramework(Map<String, List<String>> framework) {
        this.framework = framework;
    }

    public Boolean getPhoneVerified() {
        return phoneVerified;
    }

    public void setPhoneVerified(Boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }


    public String getTncAcceptedVersion() {

        return tncAcceptedVersion;
    }

    public void setTncAcceptedVersion(String tncAcceptedVersion) {
        this.tncAcceptedVersion = tncAcceptedVersion;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public List<JobProfile> getJobProfile() {
        return jobProfile;
    }

    public void setJobProfile(List<JobProfile> jobProfile) {
        this.jobProfile = jobProfile;
    }

    public List<Education> getEducation() {
        return education;
    }

    public void setEducation(List<Education> education) {
        this.education = education;
    }


}
