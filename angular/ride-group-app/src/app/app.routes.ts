import { Routes } from '@angular/router';
import { Home } from './features/home/home';
import { MainLayout } from './layout/main-layout/main-layout';
import { Profile } from './features/profile/profile';
import { authGuardGuard } from './guards/auth-guard-guard';
import { Login } from './features/login/login';
import { RideGroups } from './features/ride-groups/ride-groups';

export const routes: Routes = [
  {
    path: '',
    component: MainLayout,
    children: [
      {
        path: '',
        component: Home,
      },
      {
        path: 'login',
        component: Login,
      },
      {
        path: 'profile',
        component: Profile,
        canActivate: [authGuardGuard],
      },
      {
        path: 'group',
        component: Profile,
        canActivate: [authGuardGuard],
      },
      {
        // The ":id" is dynamic. If you go to /group/5, the ID will be 5.
        path: 'group/:id',
        component: RideGroups,
        canActivate: [authGuardGuard],
        title: 'RideSync - Live Map',
      },
    ],
  },
  { path: '', redirectTo: '', pathMatch: 'full' },
  { path: '**', redirectTo: '' },
];
