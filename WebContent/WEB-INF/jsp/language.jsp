<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Language Selection</title>

		<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.0.4/angular.min.js"></script>

		<script>
		function LanguageListCtrl($scope, $element, $http)
		{
			$scope.dataUrl = '${pageContext.request.contextPath}/language/languagelist.json';

			$scope.submitUrl = '${pageContext.request.contextPath}/language/submitdata';

			$scope.languageList =
				$http.get($scope.dataUrl).
				success(function(data, status)
				{
					$scope.languages = data.languages;
				}).
				error(function(data, status)
				{
					$scope.data = data || "Request failed";
					$scope.status = status;
				});

			//this came from http://stackoverflow.com/questions/12141035/angularjs-serialize-form-data
			$scope.submit = function()
			{
				//var elem = angular.element($element);
				//var dt = $(elem.parent()).serialize();
				console.log($element.serialize());
				$http({
					method: 'POST',
					url: $scope.submitUrl,
					//data: 'first=hgf&last=ghfgh',
					headers: {'Content-Type': 'application/x-www-form-urlencoded'}
				}).success(function(data, status) {
					//$scope.status = status;
					//$scope.data = data;
					//$scope.result = data; // Show result from server in our <pre></pre> element
					//var elem = angular.element(e.srcElement);
					//alert($(elem.parent()).serialize());
				}).error(function(data, status) {
					$scope.data = data || "Request failed";
					$scope.status = status;
				});
				return false;
			};

			$scope.grades =
				[{"gradeId": 1, "grade": "A"},{"gradeId": 2, "grade": "A-"},{"gradeId": 3, "grade": "B+"},{"gradeId": 4, "grade": "B"},
				{"gradeId": 5, "grade": "B-"},{"gradeId": 6, "grade": "C+"},{"gradeId": 7, "grade": "C"},{"gradeId": 8, "grade": "C-"},
				{"gradeId": 9, "grade": "D+"},{"gradeId": 10, "grade": "D"},{"gradeId": 11, "grade": "D-"},{"gradeId": 12, "grade": "F"}];

			$scope.fullVersion = angular.version;
			$scope.chosenNativeLanguage = 3;
			$scope.chosenLanguageGrade = 5;
		}
		</script>
	</head>

	<body>

		<%@ include file="commonheader.jsp" %>
		<form ng-controller="LanguageListCtrl" ng-submit="submit()">
			<div class="languageSelector">
				<table>
					<tr><td>Native Language</td><td class="languageSelectorCellPad">Average grade</td></tr>
					<tr>
						<td>
							<select
								name="native_language"
								id="nativeLanguage"
								ng-model="chosenNativeLanguage"
								ng-options="obj.languageId as obj.language + ' ' + '(' + obj.languageId + ')' for obj in languages">
								<option value=''>select</option>
							</select>
						</td>
						<td class="languageSelectorCellPad">
							<select
								name="native_language_grade"
								id="languageGrade"
								ng-model="chosenLanguageGrade"
								ng-options="obj.gradeId as obj.grade for obj in grades">
								<option value=''>--</option>
							</select>
						</td>
					</tr>
				</table>
			</div>

			<div class="languageSelector">
				<table>
					<tr>
						<td>Indicate all languages that you speak</td><td class="languageSelectorCellPad">Native speaker</td>
					</tr>
					<tr>
						<td>
							<select name="language_1">
								<option value='' selected='selected'>select</option>
								<option value="english">English</option>
								<option value="spanish">Spanish</option>
								<option value="portuguese">Portuguese</option>
							</select>
						</td>
						<td class="languageSelectorCellPad">
							<input type="radio" name="language_1_native" value="yes">Yes
							&nbsp;
							<input type="radio" name="language_1_native" value="no">No
						</td>
					</tr>
					<tr>
						<td>
							<select name="language_2">
								<option value='' selected='selected'>select</option>
								<option value="english">English</option>
								<option value="spanish">Spanish</option>
								<option value="portuguese">Portuguese</option>
							</select>
						</td>
						<td class="languageSelectorCellPad">
							<input type="radio" name="language_2_native" value="yes">Yes
							&nbsp;
							<input type="radio" name="language_2_native" value="no">No
						</td>
					</tr>
				</table>
			</div>
			<div class="languageSelector">
				<button type="submit" class="btn">Submit</button>
			</div>
		</form>
		<br />
		<br />
		<br />
		<hr />
		<!-- Note that these spans are outside the <div> where we set the controller to be
		LanguageListCtrl so the controller must again be assigned so that it applies to these spans -->
		<span ng-controller="LanguageListCtrl">AngularJS Version {{fullVersion}}</span>
	</body>
</html>