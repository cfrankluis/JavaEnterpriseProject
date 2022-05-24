const url = window.location.href;
export const ip = url.split('/')[2].split(':')[0];
const port  = url.split('/')[2].split(':')[1];