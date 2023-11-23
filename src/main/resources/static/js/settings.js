const selectMember = (member) => {
  const { email, password } = member;
  const string = `${email}:${password}`;
  localStorage.setItem('credentials', btoa(string));
  alert(`${email} 사용자로 설정했습니다.`)
}